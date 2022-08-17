open class NoteService: CrudService<Note> {
    var notes = emptyArray<Note>()
    open var comments = emptyArray<Comments>()
    private var noteId: Int = 0

    override fun add(elem: Note): Note {
        var size = notes.size
        noteId += notes.hashCode()
        notes += elem.copy(id = size + 1)
        return notes.last()
    }

    override fun createComment(elem: Note, comment: Comments): Comments {
        var size = comments.size
        comments += comment.copy(noteId = elem.id, id = size + 1)
        return comments.last()
    }

    override fun delete(elem: Note): Boolean {
        var flag = false
        for ((index, oneElem) in notes.withIndex())
            if (elem.id == oneElem.id) {
                if(oneElem.deleteNote != true) {
                    notes[index] = elem.copy(deleteNote = true)
                    flag = true
                    for ((index, oneElem) in comments.withIndex())
                        if (elem.id == oneElem.noteId) {
                            comments[index] = oneElem.copy(deleteComment = true)
                        }
                }
            }
        return flag
    }

    override fun deleteComment(elem: Note, comment: Comments): Boolean {
        var flag = false

        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && comment.id == oneElem.id) {
                comments[index] = oneElem.copy(deleteComment = true)
                notes[elem.id].comments --
                flag = true
            }

        return flag
    }

    override fun edit(elem: Note, message: String): Note {
        var flagNote = elem

        for ((index, oneElem) in notes.withIndex())
            if (elem.id == oneElem.id && oneElem.deleteNote != true) {
                notes[index] = oneElem.copy(text = message)
            }
        return flagNote
    }

    override fun editComment(elem: Note, comment: Comments, message: String): Comments {
        var flagComments : Comments = comment
        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && oneElem.id == comment.id) {
                comments[index] = oneElem.copy(message = message)
                flagComments = comments[index]
            }
        return flagComments
    }

    override fun get(userId: Int): Array<Note> {
        var notesGetArray = emptyArray<Note>()
        for ((index, oneElem) in notes.withIndex())
            if (userId == oneElem.ownerId && oneElem.deleteNote != true) {
                notesGetArray += oneElem
            }
        return notesGetArray
    }

    override fun getById(elemId: Int): Note {
        return notes[elemId]
    }

    override fun getComments(elem: Note): Array<Comments> {
        var commentsGetArray = emptyArray<Comments>()
        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && oneElem.deleteComment != true) {
                commentsGetArray += oneElem
            }
        return commentsGetArray
    }

    override fun restoreComment(elem: Note, comment: Comments): Comments {
        var flagComment = comment

        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && oneElem.deleteComment == true) {
                comments[index] = oneElem.copy(deleteComment = false)
                flagComment = comments[index]
            }
        return flagComment
    }
}