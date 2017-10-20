
package org.me.cearch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.cearch package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RunSeverResponse_QNAME = new QName("http://cearch.me.org/", "runSeverResponse");
    private final static QName _IOException_QNAME = new QName("http://cearch.me.org/", "IOException");
    private final static QName _DoSearch_QNAME = new QName("http://cearch.me.org/", "doSearch");
    private final static QName _GetDateDiff_QNAME = new QName("http://cearch.me.org/", "getDateDiff");
    private final static QName _GetDateDiffResponse_QNAME = new QName("http://cearch.me.org/", "getDateDiffResponse");
    private final static QName _DoSearchResponse_QNAME = new QName("http://cearch.me.org/", "doSearchResponse");
    private final static QName _RunSever_QNAME = new QName("http://cearch.me.org/", "runSever");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.cearch
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoSearchResponse }
     * 
     */
    public DoSearchResponse createDoSearchResponse() {
        return new DoSearchResponse();
    }

    /**
     * Create an instance of {@link RunSever }
     * 
     */
    public RunSever createRunSever() {
        return new RunSever();
    }

    /**
     * Create an instance of {@link GetDateDiff }
     * 
     */
    public GetDateDiff createGetDateDiff() {
        return new GetDateDiff();
    }

    /**
     * Create an instance of {@link GetDateDiffResponse }
     * 
     */
    public GetDateDiffResponse createGetDateDiffResponse() {
        return new GetDateDiffResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link DoSearch }
     * 
     */
    public DoSearch createDoSearch() {
        return new DoSearch();
    }

    /**
     * Create an instance of {@link RunSeverResponse }
     * 
     */
    public RunSeverResponse createRunSeverResponse() {
        return new RunSeverResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunSeverResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "runSeverResponse")
    public JAXBElement<RunSeverResponse> createRunSeverResponse(RunSeverResponse value) {
        return new JAXBElement<RunSeverResponse>(_RunSeverResponse_QNAME, RunSeverResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoSearch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "doSearch")
    public JAXBElement<DoSearch> createDoSearch(DoSearch value) {
        return new JAXBElement<DoSearch>(_DoSearch_QNAME, DoSearch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateDiff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "getDateDiff")
    public JAXBElement<GetDateDiff> createGetDateDiff(GetDateDiff value) {
        return new JAXBElement<GetDateDiff>(_GetDateDiff_QNAME, GetDateDiff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateDiffResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "getDateDiffResponse")
    public JAXBElement<GetDateDiffResponse> createGetDateDiffResponse(GetDateDiffResponse value) {
        return new JAXBElement<GetDateDiffResponse>(_GetDateDiffResponse_QNAME, GetDateDiffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoSearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "doSearchResponse")
    public JAXBElement<DoSearchResponse> createDoSearchResponse(DoSearchResponse value) {
        return new JAXBElement<DoSearchResponse>(_DoSearchResponse_QNAME, DoSearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunSever }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cearch.me.org/", name = "runSever")
    public JAXBElement<RunSever> createRunSever(RunSever value) {
        return new JAXBElement<RunSever>(_RunSever_QNAME, RunSever.class, null, value);
    }

}
