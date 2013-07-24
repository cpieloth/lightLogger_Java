package executor.lightLogger.logger;

import executor.lightLogger.Logger;
import executor.lightLogger.level.ILevel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileLogger extends ALoggerWriter {

    // Should be static/singleton
    private Writer out = null;

    public FileLogger() {
        this(ILogger.UNKNOWN_NAME);
    }

    public FileLogger(String source) {
        super(source);
    }

    @Override
    protected void finalize() throws Throwable {
        if (out != null)
            out.close();
        super.finalize();
    }

    @Override
    public void log(ILevel level, Object message) {
        super.log(out, level, message);
    }

    @Override
    public void log(ILevel level, Object message, Throwable throwable) {
        super.log(out, level, message, throwable);
    }

    public boolean setFile(String file) {
        try {
            if (out != null)
                out.close();

            out = new BufferedWriter(new FileWriter(file, true));
        } catch (Exception e) {
            out = new OutputStreamWriter(System.out);
            Logger.error(FileLogger.class,
                    "Could not open file! Using System.out instead.");
            return false;
        }
        return true;
    }

}
