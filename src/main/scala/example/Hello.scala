package example

import workshoplib.Library
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}
import zio.http.{Method, Response, Routes, Server, handler}

object Hello extends ZIOAppDefault {

  private val routes: Routes[Any, Response] =
    Routes(
      Method.GET / "hello" ->
        handler(Response.text(Library.threeTimes("Hello, World!"))),
    )

  override def run: ZIO[ZIOAppArgs with Scope, Any, Any] =
    Server.serve(routes).provide(Server.defaultWithPort(8081))
}