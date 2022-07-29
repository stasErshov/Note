interface CrudService<T> {
    fun add(elem: T) : T
    fun edit(elem: T) : Boolean
    fun createComment(elem: T, comment: Comments) : Comments
    fun getComments(elem: T) : Array<Comments>
    fun getById(elemId: Int) : T
    fun get(userId: Int) : Array<T>
    fun editComment(elem: T, idComment: Int, message: String): Boolean

}