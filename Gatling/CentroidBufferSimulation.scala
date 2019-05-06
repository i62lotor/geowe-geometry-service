package org.geowe.ggs.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CentroidBufferSimulation extends Simulation {

  private val httpConf = http
    .baseUrl("http://127.0.0.1:8080/ggs/operations/jts")
    .acceptHeader("application/json")

  private val polygon = """{"crs":"WGS84","id":"polygon-1","wkt":"POLYGON((-4.7687530517578125 37.633483617951576,-4.783172607421875 37.60084982441606,-4.7055816650390625 37.584527557100245,-4.7124481201171875 37.63783370818002,-4.7687530517578125 37.633483617951576))"}"""

  private val centroidScn: ScenarioBuilder = scenario("Centroid Scenario")
    .exec(http("Get Centroid")
    .post("/centroid/")
    .body(StringBody(polygon)).asJson
    .check(status.is(201)))

  private val bufferScn: ScenarioBuilder = scenario("Buffer Scenario")
    .exec(http("Get Buffer")
    .post("/buffer/")
    .body(StringBody(polygon)).asJson
    .check(status.is(201)))
	
  setUp(
    centroidScn.inject(rampUsers(500) during (60 seconds)),
    bufferScn.inject(rampUsers(500) during (60 seconds))    
  ).protocols(httpConf)
}
