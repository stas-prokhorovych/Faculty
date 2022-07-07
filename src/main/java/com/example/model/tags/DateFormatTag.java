package com.example.model.tags;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatTag extends TagSupport{
    private static final Logger LOG = LogManager.getLogger(DateFormatTag.class);

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";

    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Change format of DataTime in jsp view
     *
     * @return constant
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

            out.write(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN).format(localDateTime));
        } catch (IOException e) {
            LOG.trace("Could not write date after format.", e);
        }
        return SKIP_BODY;
    }
}
