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
    private var commentForFirst1 : Comments = Comments(1, 1, 2, "asdfasdf")
    private var commentForFirst2 : Comments = Comments(1, 2, 2, "asdfasdf")
    private var commentForSecond1 : Comments = Comments(2, 1, 2, "asdfasdf")
    private var commentForSecond2 : Comments = Comments(2, 1, 2, "asdfasdf")
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
        realized.createComment(note1, commentForFirst2)
        realized.createComment(note2, commentForSecond2)
        realized.createComment(note2, commentForSecond1)
        realized.deleteComment(note1, commentForFirst2)
        var commentsGetArray = emptyArray<Comments>()
        commentsGetArray += commentForFirst1
        val arrTest = realized.getComments(note1)
        val isEqels =  commentsGetArray.contentEquals(arrTest)
        assertEquals(true, isEqels)
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
        val isEqels =  notesGetArray.contentEquals(arrTest)
        assertEquals(true, isEqels)
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
}