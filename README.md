#devneko-kotlin-jdbc(kjdbc)

kjdbcはKotlinのためのシンプルなJDBCヘルパーです。  
Kotlinの強力なシンタックスを活用してJDBCをより簡単に扱えます。

##機能

 - 名前付きプレースホルダー
 - StatementやResultSetに対するシンプルなパラメータ設定/取得
 - レコードのデータクラスへのマッピング

##コード例

```kotlin
data class Post(
  id:Int,
  userId:Int,
  body:String,
  created:Date
)

val helper = SqlHelper(connection)

helper().update("INSERT INTO post(id,user_id,body,path,created) VALUES (:id,:user_id,:body,:created)") {
            set("id", 1)
            set("user_id", 1)
            set("body", "body")
            set("created", java.util.Date())
         }
val rs = helper.query("SELECT * FROM post WHERE id = :id") {
  set("id", 1)
}
rs.forEach(Post::class) {
  println(it)
}
rs.close()
// または
// while ( rs.next() ) {
//   val post:Post = rs.read()
//   println(post)
// }
//
```


