# devneko-kotlin-jdbc(kjdbc)

kjdbc is a simple helper library for JDBC.  s
kjdbc makes easier to handle a database by Kotlin's excellent syntax.

## Features

 - Named Placeholder
 - Simple notation for setting parameters to Statement or ResultSet
 - Mapping to data class

## Example

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

// Or
// while ( rs.next() ) {
//   val post:Post = rs.readOne()
//   println(post)
// }
//

helper.delete("post", "id = :id") {
  set("id", 1)
}

```


