import io.getquill._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Quill extends App {

  val db = new CassandraAsyncContext[SnakeCase]("db")
  import db._

  case class WeatherStation(country: String, city: String, stationId: String, entry: Int, value: Int)

  object WeatherStation {

    val getAllByCountry = quote {
      (country: String) =>
        query[WeatherStation].filter(_.country == country)
    }

    val getAllByCountryAndCity = quote {
      (country: String, city: String) =>
        getAllByCountry(country).filter(_.city == city)
    }

    val getAllByCountryCityAndId = quote {
      (country: String, city: String, stationId: String) =>
        getAllByCountryAndCity(country, city).filter(_.stationId == stationId)
    }
  }


  val result=db.run(query[WeatherStation].insert(lift(WeatherStation("DE", "Dussel", "DUS",2,4))))

//  val result = db.run(WeatherStation.getAllByCountryCityAndId("UK", "London", "SW2"))
  val queryResult=Await.result(result, 2 seconds)
  println(queryResult.toString)
  result.onComplete(_ => db.close())
}
