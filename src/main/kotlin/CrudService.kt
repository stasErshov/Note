interface CrudService<T> {
    fun add(elem: T) : T
    fun createComment(elem: T, comment: Comments) : Comments
    fun delete(elem: T) : Boolean
    fun deleteComment(elem: T, comment: Comments) : Boolean
    fun edit(elem: T, message: String): T
    fun editComment(elem: T, comment: Comments, message: String): Comments
    fun get(userId: Int) : Array<T>
    fun getById(elemId: Int) : T
    fun getComments(elem: T) : Array<Comments>
    fun restoreComment(elem: T, comment: Comments) : Comments

}