package com.final_project.app.model;

/**
 * FIXME : Document this class.
 *
 * @author aholt (aholt@i2rd.com)
 * @since 12/8/14 3:14 PM
 */
public class SlugNotPresentException extends IllegalArgumentException
{
    private static final String baseMsg = "The specified slug was not present within the database: ";

    public SlugNotPresentException(String slug) {
        super(baseMsg + slug);
    }
}
