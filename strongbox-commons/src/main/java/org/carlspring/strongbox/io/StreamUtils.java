package org.carlspring.strongbox.io;

import org.carlspring.commons.http.range.ByteRange;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtils
{

    private StreamUtils()
    {

    }

    public static long getLength(ArtifactInputStream is)
            throws IOException
    {
        InputStream target = is.getTarget();
        if (!(target instanceof ByteRangeInputStream))
        {
            return -1L;
        }
        ByteRangeInputStream bris = (ByteRangeInputStream) target;
        return bris.getLength();
    }

    public static void setCurrentByteRange(ArtifactInputStream is,
                                           ByteRange byteRange)
            throws IOException
    {
        InputStream target = is.getTarget();
        if (!(target instanceof ByteRangeInputStream))
        {
            return;
        }
        ByteRangeInputStream bris = (ByteRangeInputStream) target;
        bris.setCurrentByteRange(byteRange);
        bris.skip(byteRange.getOffset());
    }

}
