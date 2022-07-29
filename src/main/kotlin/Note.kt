data class Comments(val noteId: Int,
                    val id: Int,
                    val ownerId : Int,
                    val message: String)

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var comments: Int = 0,
    val readComments: Int,
    val viewUrl: String
)