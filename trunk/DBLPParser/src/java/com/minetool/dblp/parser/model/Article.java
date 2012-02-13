//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.12 at 05:30:38 PM SGT 
//


package com.minetool.dblp.parser.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter"
})
@XmlRootElement(name = "article")
public class Article extends AbstractDblpObj{

    @XmlAttribute(name = "key", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String key;
    @XmlAttribute(name = "reviewid")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String reviewid;
    @XmlAttribute(name = "rating")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String rating;
    @XmlAttribute(name = "mdate")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String mdate;
    @XmlElements({
        @XmlElement(name = "author", type = Author.class),
        @XmlElement(name = "editor", type = Editor.class),
        @XmlElement(name = "title", type = Title.class),
        @XmlElement(name = "booktitle", type = Booktitle.class),
        @XmlElement(name = "pages", type = Pages.class),
        @XmlElement(name = "year", type = Year.class),
        @XmlElement(name = "address", type = Address.class),
        @XmlElement(name = "journal", type = Journal.class),
        @XmlElement(name = "volume", type = Volume.class),
        @XmlElement(name = "number", type = Number.class),
        @XmlElement(name = "month", type = Month.class),
        @XmlElement(name = "url", type = Url.class),
        @XmlElement(name = "ee", type = Ee.class),
        @XmlElement(name = "cdrom", type = Cdrom.class),
        @XmlElement(name = "cite", type = Cite.class),
        @XmlElement(name = "publisher", type = Publisher.class),
        @XmlElement(name = "note", type = Note.class),
        @XmlElement(name = "crossref", type = Crossref.class),
        @XmlElement(name = "isbn", type = Isbn.class),
        @XmlElement(name = "series", type = Series.class),
        @XmlElement(name = "school", type = School.class),
        @XmlElement(name = "chapter", type = Chapter.class)
    })
    protected List<Object> authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the reviewid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewid() {
        return reviewid;
    }

    /**
     * Sets the value of the reviewid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewid(String value) {
        this.reviewid = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRating(String value) {
        this.rating = value;
    }

    /**
     * Gets the value of the mdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * Sets the value of the mdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdate(String value) {
        this.mdate = value;
    }

    /**
     * Gets the value of the authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     * {@link Editor }
     * {@link Title }
     * {@link Booktitle }
     * {@link Pages }
     * {@link Year }
     * {@link Address }
     * {@link Journal }
     * {@link Volume }
     * {@link Number }
     * {@link Month }
     * {@link Url }
     * {@link Ee }
     * {@link Cdrom }
     * {@link Cite }
     * {@link Publisher }
     * {@link Note }
     * {@link Crossref }
     * {@link Isbn }
     * {@link Series }
     * {@link School }
     * {@link Chapter }
     * 
     * 
     */
    public List<Object> getAuthorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter() {
        if (authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter == null) {
            authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter = new ArrayList<Object>();
        }
        return this.authorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter;
    }

    /* (non-Javadoc)
     * @see com.minetool.dblp.parser.model.AbstractDblpObj#getTitle()
     */
    @Override
    public String getTitle() {
	List<Object> objList = getAuthorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter();
	for (Object object : objList) {
	    if (object instanceof Title) {
		Title title = (Title) object;
		return title.getvalue();
	    }
	}
	return ""; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see com.minetool.dblp.parser.model.AbstractDblpObj#getBookTitle()
     */
    @Override
    public String getBookTitle() {
	List<Object> objList = getAuthorOrEditorOrTitleOrBooktitleOrPagesOrYearOrAddressOrJournalOrVolumeOrNumberOrMonthOrUrlOrEeOrCdromOrCiteOrPublisherOrNoteOrCrossrefOrIsbnOrSeriesOrSchoolOrChapter();
	for (Object object : objList) {
	    if (object instanceof Booktitle) {
		Booktitle bookTitle = (Booktitle) object;
		return bookTitle.getvalue();
	    }
	}
	return ""; //$NON-NLS-1$
    }

}
