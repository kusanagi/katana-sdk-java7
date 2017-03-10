/*
 * Java 7 SDK for the KATANA(tm) Platform (http://katana.kusanagi.io)
 * Copyright (c) 2016-2017 KUSANAGI S.L. All rights reserved.
 *
 * Distributed under the MIT license
 *
 * For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code
 *
 * @link      https://github.com/kusanagi/katana-sdk-java7
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 * @copyright Copyright (c) 2016-2017 KUSANAGI S.L. (http://kusanagi.io)
 *
 */

package io.kusanagi.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kusanagi.katana.api.Api;
import io.kusanagi.katana.api.component.Component;
import io.kusanagi.katana.api.component.Key;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api {

    /**
     * The meta-information about the payload
     */
    @JsonProperty(Key.RESPONSE_META)
    private Meta meta;

    /**
     * The semantics of the original request
     */
    @JsonProperty(Key.RESPONSE_HTTP_REQUEST)
    private HttpRequest httpRequest;

    /**
     * The semantics of the response
     */
    @JsonProperty(Key.RESPONSE_HTTP_RESPONSE)
    private HttpResponse httpResponse;

    /**
     * The Transport instance
     */
    @JsonProperty(Key.RESPONSE_TRANSPORT)
    private Transport transport;

    public Response() {
        // Default constructor to make possible the serialization of this object.
    }

    public Response(Response other) {
        super(other);
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.httpResponse = other.httpResponse;
        this.transport = other.transport;
    }

    public Response(Component component, String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(component, path, name, version, platformVersion, variables, isDebug);
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    // SDK METHODS

    /**
     * @return the protocol implemented by the Gateway component handling the request.
     */
    @JsonIgnore
    public String getGatewayProtocol() {
        return this.meta.getProtocol();
    }

    /**
     * @return the public address of the Gateway component handling the request.
     */
    @JsonIgnore
    public String getGatewayAddress() {
        return this.meta.getGateway().get(1);
    }

    /**
     * @return the instance of the HttpRequest class which contains the HTTP semantics of the request made to the Gateway component.
     */
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    /**
     * @return Return the instance of the HttpResponse class which contains the HTTP semantics for the response to be
     * made from the Gateway component.
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     * @return Return an instance of the deserialize-only Transport interface.
     */
    public Transport getTransport() {
        return transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Response)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Response response = (Response) o;

        if (getMeta() != null ? !getMeta().equals(response.getMeta()) : response.getMeta() != null) {
            return false;
        }
        if (getHttpRequest() != null ? !getHttpRequest().equals(response.getHttpRequest()) : response.getHttpRequest() != null) {
            return false;
        }
        if (getHttpResponse() != null ? !getHttpResponse().equals(response.getHttpResponse()) : response.getHttpResponse() != null) {
            return false;
        }
        return getTransport() != null ? getTransport().equals(response.getTransport()) : response.getTransport() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMeta() != null ? getMeta().hashCode() : 0);
        result = 31 * result + (getHttpRequest() != null ? getHttpRequest().hashCode() : 0);
        result = 31 * result + (getHttpResponse() != null ? getHttpResponse().hashCode() : 0);
        result = 31 * result + (getTransport() != null ? getTransport().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", httpResponse=" + httpResponse +
                ", transport=" + transport +
                "} " + super.toString();
    }
}
