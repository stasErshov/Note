import NoteService.*

fun main() {
    var note1: Note = Note(
        id = 1,
        ownerId = 2,
        title = "aa",
        text = "aa",
        date = 1,
        comments = 0,
        readComments = 0,
        viewUrl = "aa"
    )
    var note2: Note = Note(
        id = 1,
        ownerId = 2,
        title = "aa",
        text = "aa",
        date = 1,
        comments = 0,
        readComments = 0,
        viewUrl = "aa"
    )
    var commentForFirst : Comments = Comments(1, 1, 2, "asdfasdf")
    val realized = NoteService()
    realized.add(note1)
    realized.add(note2)
    realized.createComment(note1, commentForFirst)
    for ((index, oneElem) in realized.comments.withIndex()) {
        println(oneElem)
        println(index)
    }
    var comments = emptyArray<Comments>()
    comments += commentForFirst
    for ((index, oneElem) in comments.withIndex()) {
        println(oneElem)
        println(index)
    }
    val isEqual = comments.contentEquals(realized.comments)
    println(isEqual)
    println(comments)
    println(realized.getComments(note1))

}