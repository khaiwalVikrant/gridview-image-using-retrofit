
package vikrant.khaiwal.displayimage.restModel;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    @Expose private Integer numFound;
    @Expose private Integer start;
    @Expose private List<Doc> docs = new ArrayList<Doc>();
    @Expose private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The numFound
     */
    public Integer getNumFound() {
        return numFound;
    }

    /**
     * 
     * @param numFound
     *     The numFound
     */
    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    /**
     * 
     * @return
     *     The start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The docs
     */
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     * 
     * @param docs
     *     The docs
     */
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
