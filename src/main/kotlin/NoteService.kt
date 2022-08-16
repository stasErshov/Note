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

    override fun edit(elem: Note, text: String): Boolean {
        var flag = false
        for ((index, oneElem) in notes.withIndex())
            if (elem.id == oneElem.id) {
                notes[index] = elem.copy(ownerId = oneElem.ownerId, date = oneElem.date, text = text)
                flag = true
            }
        return flag
    }

    override fun createComment(elem: Note, comment: Comments): Comments {
        var size = comments.size
        comments += comment.copy(id = size + 1)
        elem.comments += 1
        return comments.last()
    }

    override fun getComments(elem: Note): Array<Comments> {
        var commentsGetArray = emptyArray<Comments>()
        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId) {
                commentsGetArray += oneElem
            }
        return commentsGetArray
    }

    override fun getById(elemId: Int): Note {
        return notes[elemId]
    }

    override fun get(userId: Int): Array<Note> {
        var notesGetArray = emptyArray<Note>()
        for ((index, oneElem) in notes.withIndex())
            if (userId == oneElem.ownerId) {
                notesGetArray += oneElem
            }
        return  notesGetArray
    }

    override fun editComment(elem: Note, idComment: Int, message: String): Boolean {
        var flag = false
        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && idComment == oneElem.id) {
                comments[index] = oneElem.copy(message = message)
                flag = true
            }
        return flag
    }
}