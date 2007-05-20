//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.05.19 at 04:51:31 AM BST 
//


package mismo;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ApplicationTakenMethodType" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="InterviewerApplicationSignedDate" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="InterviewersEmployerCity" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="InterviewersEmployerName" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="InterviewersEmployerPostalCode" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="InterviewersEmployerState" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="InterviewersEmployerStreetAddress" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="InterviewersTelephoneNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "INTERVIEWER_INFORMATION")
public class INTERVIEWERINFORMATION {

    @XmlAttribute(name = "ApplicationTakenMethodType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String applicationTakenMethodType;
    @XmlAttribute(name = "InterviewerApplicationSignedDate", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String interviewerApplicationSignedDate;
    @XmlAttribute(name = "InterviewersEmployerCity", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String interviewersEmployerCity;
    @XmlAttribute(name = "InterviewersEmployerName", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String interviewersEmployerName;
    @XmlAttribute(name = "InterviewersEmployerPostalCode", required = true)
    protected BigInteger interviewersEmployerPostalCode;
    @XmlAttribute(name = "InterviewersEmployerState", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String interviewersEmployerState;
    @XmlAttribute(name = "InterviewersEmployerStreetAddress", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String interviewersEmployerStreetAddress;
    @XmlAttribute(name = "InterviewersTelephoneNumber", required = true)
    protected BigInteger interviewersTelephoneNumber;

    /**
     * Gets the value of the applicationTakenMethodType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationTakenMethodType() {
        return applicationTakenMethodType;
    }

    /**
     * Sets the value of the applicationTakenMethodType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationTakenMethodType(String value) {
        this.applicationTakenMethodType = value;
    }

    /**
     * Gets the value of the interviewerApplicationSignedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterviewerApplicationSignedDate() {
        return interviewerApplicationSignedDate;
    }

    /**
     * Sets the value of the interviewerApplicationSignedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterviewerApplicationSignedDate(String value) {
        this.interviewerApplicationSignedDate = value;
    }

    /**
     * Gets the value of the interviewersEmployerCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterviewersEmployerCity() {
        return interviewersEmployerCity;
    }

    /**
     * Sets the value of the interviewersEmployerCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterviewersEmployerCity(String value) {
        this.interviewersEmployerCity = value;
    }

    /**
     * Gets the value of the interviewersEmployerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterviewersEmployerName() {
        return interviewersEmployerName;
    }

    /**
     * Sets the value of the interviewersEmployerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterviewersEmployerName(String value) {
        this.interviewersEmployerName = value;
    }

    /**
     * Gets the value of the interviewersEmployerPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInterviewersEmployerPostalCode() {
        return interviewersEmployerPostalCode;
    }

    /**
     * Sets the value of the interviewersEmployerPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInterviewersEmployerPostalCode(BigInteger value) {
        this.interviewersEmployerPostalCode = value;
    }

    /**
     * Gets the value of the interviewersEmployerState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterviewersEmployerState() {
        return interviewersEmployerState;
    }

    /**
     * Sets the value of the interviewersEmployerState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterviewersEmployerState(String value) {
        this.interviewersEmployerState = value;
    }

    /**
     * Gets the value of the interviewersEmployerStreetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterviewersEmployerStreetAddress() {
        return interviewersEmployerStreetAddress;
    }

    /**
     * Sets the value of the interviewersEmployerStreetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterviewersEmployerStreetAddress(String value) {
        this.interviewersEmployerStreetAddress = value;
    }

    /**
     * Gets the value of the interviewersTelephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInterviewersTelephoneNumber() {
        return interviewersTelephoneNumber;
    }

    /**
     * Sets the value of the interviewersTelephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInterviewersTelephoneNumber(BigInteger value) {
        this.interviewersTelephoneNumber = value;
    }

}