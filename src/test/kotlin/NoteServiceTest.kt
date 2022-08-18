@file:Suppress("DEPRECATION")

import junit.framework.Assert.assertEquals
import org.junit.Test

class NoteServiceTest {

    private var note1: Note = Note(
        id = 1,
        ownerId = 1,
        title = "aa",
        text = "aa",
        date = 1,
        comments = 0,
        readComments = 0,
        viewUrl = "aa"
    )
    private var note2: Note = Note(
        id = 2,
        ownerId = 2,
        title = "aa",
        text = "aa",
        date = 1,
        comments = 0,
        readComments = 0,
        viewUrl = "aa"
    )
    private var note3: Note = Note(
        id = 3,
        ownerId = 1,
        title = "aa",
        text = "aa",
        date = 1,
        comments = 0,
        readComments = 0,
        viewUrl = "aa"
    )
    private var commentForFirst1 : Comments = Comments(1, 1, 2, "")
    private var commentForFirst2 : Comments = Comments(1, 3, 2, "")
    private var commentForSecond1 : Comments = Comments(2, 2, 2, "")
    private var commentForSecond2 : Comments = Comments(2, 4, 2, "")
    private val realized = NoteService()

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

    @Test
    fun getByIdTest(){
        realized.add(note1)
        val note = realized.getById(0)
        assertEquals(note1, note)
    }

    @Test
    fun getCommentsTest(){
        realized.add(note1)
        realized.add(note2)
        realized.createComment(note1, commentForFirst1)
        realized.createComment(note2, commentForSecond1)
        realized.createComment(note1, commentForFirst2)
        realized.createComment(note2, commentForSecond2)
        realized.deleteComment(note1, commentForFirst2)
        var commentsGetArray = emptyArray<Comments>()
        commentsGetArray += commentForFirst1
        val arrTest = realized.getComments(note1)
        assertEquals(true, commentsGetArray.contentEquals(arrTest))
    }

    @Test
    fun getTest(){
        realized.add(note1)
        realized.add(note2)
        realized.add(note3)
        realized.delete(note3)
        realized.delete(note2)
        val arrTest = realized.get(1)
        var notesGetArray = emptyArray<Note>()
        notesGetArray += note1
        assertEquals(true, notesGetArray.contentEquals(arrTest))
    }

    @Test
    fun deleteTest1(){
        realized.add(note1)
        realized.add(note2)
        realized.createComment(note1, commentForFirst1)
        realized.createComment(note2, commentForSecond1)
        val resTest = realized.delete(note1)
        var arr = false
        for (oneElem in realized.comments)
            if (note1.id == oneElem.noteId && oneElem.deleteComment) {
                arr = true
            }
        var res = false
        if (resTest && arr)
            res = true
        assertEquals(true, res)
    }

    @Test
    fun deleteTest2(){
        realized.add(note1)
        realized.delete(note1)
        val resTest = realized.delete(note1)
        assertEquals(false, resTest)
    }

    @Test
    fun editTest(){
        realized.add(note1)
        realized.add(note2)
        val textTest = realized.edit(note2, "new text").text
        assertEquals("new text", textTest)
    }

    @Test
    fun editCommentTest(){
        realized.add(note1)
        realized.add(note2)
        realized.createComment(note1, commentForFirst1)
        realized.createComment(note2, commentForSecond1)
        realized.createComment(note1, commentForFirst2)
        realized.createComment(note2, commentForSecond2)

        val textTest = realized.editComment(note1, commentForFirst2, "new text").message
        assertEquals("new text", textTest)
    }

    @Test
    fun restoreCommentTest(){
        realized.add(note1)
        realized.add(note2)
        realized.createComment(note1, commentForFirst1)
        realized.createComment(note2, commentForSecond1)
        realized.createComment(note1, commentForFirst2)
        realized.createComment(note2, commentForSecond2)
        realized.deleteComment(note2, commentForSecond2)

        val textTest = realized.restoreComment(note2, commentForSecond2).deleteComment
        assertEquals(false, textTest)
    }
}