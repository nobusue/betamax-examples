import betamax.Betamax
import betamax.Recorder
import spock.lang.*
import org.junit.*
import groovyx.net.http.RESTClient
import org.apache.http.impl.conn.ProxySelectorRoutePlanner

class MySpec extends Specification {
	
	@Rule Recorder recorder = new Recorder()
	
	@Shared RESTClient http = new RESTClient()

	def setupSpec() {
		http.client.routePlanner = new ProxySelectorRoutePlanner(
			http.client.connectionManager.schemeRegistry, ProxySelector.default)
	}
	
	@Betamax(tape = "my tape")
	def "simple http get response data"(){
		when:
			def response = http.get(uri:"http://grails.org/")
		then:
			response.status == 200
	}
}
