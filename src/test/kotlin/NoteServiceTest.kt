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
    var commentForFirst2 : Comments = Comments(1, 1, 2, "asdfasdf")
    var commentForSecond1 : Comments = Comments(2, 1, 2, "asdfasdf")
    var commentForSecond2 : Comments = Comments(2, 1, 2, "asdfasdf")
    val realized = NoteService()

    @Test
    fun addTest() {
        val noteTest = realized.add(note1)
        assertEquals(note1, noteTest)
    }

    @Test
    fun editTest1() {
        realized.add(note1)
        val bool = realized.edit(note1, "asas")
        assertEquals(true, bool)
    }

    @Test
    fun editTest2() {
        realized.add(note1)
        val bool = realized.edit(note2, "asas")
        assertEquals(false, bool)
    }

    @Test
    fun createCommentTest1() {
        realized.add(note1)
        val commentTest = realized.createComment(note1, commentForFirst1)
        assertEquals(commentForFirst1, commentTest)
    }
/*    @Test
    fun getCommentsTest() {
        realized.add(note1)
        realized.createComment(note1, commentForFirst1)
        realized.createComment(note1, commentForFirst2)
        var comentArr = emptyArray<Comments>()
        comentArr += commentForFirst1
        comentArr += commentForFirst2

        realized.add(note2)
        realized.createComment(note2, commentForSecond1)
        realized.createComment(note2, commentForSecond2)
        val comments = realized.getComments(note1)
        val isEqual = comments.contentEquals(comentArr)
        assertEquals(true, isEqual)
    }*/

    @Test
    fun getByIdTest() {
        realized.add(note1)
        val note = realized.getById(0)
        assertEquals(note1, note)
    }

    @Test
    fun getImpl() {
    }

    @Test
    fun editCommentImpl() {
    }
}