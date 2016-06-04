
package vikrant.khaiwal.displayimage.restModel;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Params {

    @Expose private String q;
    @Expose private String fl;
    @Expose private String fq;
    @Expose private String rows;
    @Expose private String wt;
    @Expose private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The q
     */
    public String getQ() {
        return q;
    }

    /**
     * 
     * @param q
     *     The q
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * 
     * @return
     *     The fl
     */
    public String getFl() {
        return fl;
    }

    /**
     * 
     * @param fl
     *     The fl
     */
    public void setFl(String fl) {
        this.fl = fl;
    }

    /**
     * 
     * @return
     *     The fq
     */
    public String getFq() {
        return fq;
    }

    /**
     * 
     * @param fq
     *     The fq
     */
    public void setFq(String fq) {
        this.fq = fq;
    }

    /**
     * 
     * @return
     *     The rows
     */
    public String getRows() {
        return rows;
    }

    /**
     * 
     * @param rows
     *     The rows
     */
    public void setRows(String rows) {
        this.rows = rows;
    }

    /**
     * 
     * @return
     *     The wt
     */
    public String getWt() {
        return wt;
    }

    /**
     * 
     * @param wt
     *     The wt
     */
    public void setWt(String wt) {
        this.wt = wt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
