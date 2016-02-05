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

helper().insert("post") {
    set("id", 1)
    set("user_id", 1)
    set("body", "body")
    set("created", java.util.Date())
}

helper().update("post") {
    set("body", "new body")
    where("id = :id") {
      set("id", 1)
    }
}

helper.query("SELECT * FROM post WHERE id = :id") {
  set("id", 1)
}.use {
  it.forEach(Post::class) {
    println(it.body)
  }
}

// または
// while ( rs.next() ) {
//   val post:Post = rs.readOne()
//   println(post)
// }
//

helper.delete("post", "id = :id") {
  set("id", 1)
}

```


