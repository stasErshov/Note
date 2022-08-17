import junit.framework.Assert.assertEquals
import org.junit.Test
import NoteService.*

class NoteServiceTest(){

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
        id = 2,
        ownerId = 2,
        title = "aa",
        text = "aa",
        date = 1,
        comments = 0,
        readComments = 0,
        viewUrl = "aa"
    )
    var commentForFirst1 : Comments = Comments(1, 1, 2, "asdfasdf")
    var commentForFirst2 : Comments = Comments(1, 2, 2, "asdfasdf")
    var commentForSecond1 : Comments = Comments(2, 1, 2, "asdfasdf")
    var commentForSecond2 : Comments = Comments(2, 1, 2, "asdfasdf")
    val realized = NoteService()

    @Test
    fun addTest() {
        val noteTest = realized.add(note1)
        assertEquals(note1, noteTest)
    }

    @Test
    fun createCommentsTest(){
        realized.add(note1)
        val test = realized.createComment(note1, commentForFirst1)
        assertEquals(commentForFirst1, test)
    }
}