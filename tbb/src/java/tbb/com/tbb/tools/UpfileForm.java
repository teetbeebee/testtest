package com.tbb.tools;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/** 
 * MyEclipse Struts
 * Creation date: 09-28-2007
 * 
 * XDoclet definition:
 * @struts.form name="upfileForm"
 */
public class UpfileForm extends ActionForm {
    /*
     * Generated fields
     */

    /** file property */
    private FormFile file;


    /*
     * Generated Methods
     */

    /** 
     * Method validate
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // TODO Auto-generated method stub
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    
}