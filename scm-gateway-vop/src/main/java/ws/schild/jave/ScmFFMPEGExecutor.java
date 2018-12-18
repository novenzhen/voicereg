/*
 * JAVE - A Java Audio/Video Encoder (based on FFMPEG)
 * 
 * Copyright (C) 2008-2009 Carlo Pelliccia (www.sauronsoftware.it)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ws.schild.jave;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * A ffmpeg process wrapper.
 *
 * @author Carlo Pelliccia
 */
public class ScmFFMPEGExecutor {

    private final static Log LOG = LogFactory.getLog(ScmFFMPEGExecutor.class);

    /**
     * The path of the ffmpeg executable.
     */
    private final String ffmpegExecutablePath;

    /**
     * Arguments for the executable.
     */
    private final ArrayList<String> args = new ArrayList<>();

    /**
     * The process representing the ffmpeg execution.
     */
    private Process ffmpeg = null;

    /**
     * A process killer to kill the ffmpeg process with a shutdown hook, useful
     * if the jvm execution is shutted down during an ongoing encoding process.
     */
    private ScmProcessKiller ffmpegKiller = null;

    /**
     * A stream reading from the ffmpeg process standard output channel.
     */
    private InputStream inputStream = null;

    /**
     * A stream writing in the ffmpeg process standard input channel.
     */
    private OutputStream outputStream = null;

    /**
     * A stream reading from the ffmpeg process standard error channel.
     */
    private InputStream errorStream = null;
    
    /**
     * It build the executor.
     *
     * @param ffmpegExecutablePath The path of the ffmpeg executable.
     */
    public ScmFFMPEGExecutor(String ffmpegExecutablePath) {
        this.ffmpegExecutablePath = ffmpegExecutablePath;
    }

    /**
     * Adds an argument to the ffmpeg executable call.
     *
     * @param arg The argument.
     */
    public void addArgument(String arg) {
        args.add(arg);
    }

    /**
     * Executes the ffmpeg process with the previous given arguments.
     *
     * @return process exit code
     * @throws IOException If the process call fails.
     */
    public void execute() throws IOException {
        int argsSize = args.size();
        String[] cmd = new String[argsSize + 2];
        cmd[0] = ffmpegExecutablePath;
        for (int i = 0; i < argsSize; i++)
        {
            cmd[i + 1] = args.get(i);
        }
        cmd[argsSize + 1] = "-hide_banner";  // Don't show banner
        StringBuilder sb = new StringBuilder();
            for (String c : cmd)
            {
                sb.append(c);
                sb.append("  ");
            }
            LOG.debug("About to execute " + sb.toString());
        Runtime runtime = Runtime.getRuntime();
        ffmpeg = runtime.exec(cmd);
        try{
            ffmpeg.waitFor();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        ffmpegKiller = new ScmProcessKiller(ffmpeg);
        runtime.addShutdownHook(ffmpegKiller);
        inputStream = ffmpeg.getInputStream();
        outputStream = ffmpeg.getOutputStream();
        outputStream.flush();
        errorStream = ffmpeg.getErrorStream();
    }

    public void execute(String cmd) throws IOException {
        int argsSize = args.size();
        cmd=ffmpegExecutablePath+' '+cmd;
        Runtime runtime = Runtime.getRuntime();
        ffmpeg = runtime.exec(cmd);
        try{
            ffmpeg.waitFor();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        ffmpegKiller = new ScmProcessKiller(ffmpeg);
        runtime.addShutdownHook(ffmpegKiller);
        inputStream = ffmpeg.getInputStream();
        outputStream = ffmpeg.getOutputStream();
        outputStream.flush();
        errorStream = ffmpeg.getErrorStream();
    }

    /**
     * Returns a stream reading from the ffmpeg process standard output channel.
     *
     * @return A stream reading from the ffmpeg process standard output channel.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Returns a stream writing in the ffmpeg process standard input channel.
     *
     * @return A stream writing in the ffmpeg process standard input channel.
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Returns a stream reading from the ffmpeg process standard error channel.
     *
     * @return A stream reading from the ffmpeg process standard error channel.
     */
    public InputStream getErrorStream() {
        return errorStream;
    }

    /**
     * If there's a ffmpeg execution in progress, it kills it.
     */
    public void destroy() {
        if (inputStream != null)
        {
            try
            {
                inputStream.close();
            } catch (Throwable t)
            {
                LOG.warn("Error closing input stream", t);
            }
            inputStream = null;
        }
        if (outputStream != null)
        {
            try
            {
                outputStream.close();
            } catch (Throwable t)
            {
                LOG.warn("Error closing output stream", t);
            }
            outputStream = null;
        }
        if (errorStream != null)
        {
            try
            {
                errorStream.close();
            } catch (Throwable t)
            {
                LOG.warn("Error closing error stream", t);
            }
            errorStream = null;
        }
        if (ffmpeg != null)
        {
            ffmpeg.destroy();
            ffmpeg = null;
        }
        if (ffmpegKiller != null)
        {
            Runtime runtime = Runtime.getRuntime();
            runtime.removeShutdownHook(ffmpegKiller);
            ffmpegKiller = null;
        }
    }

    /**
     * Return the exit code of the ffmpeg process
     * If the process is not yet terminated, it waits for the termination
     * of the process
     * 
     * @return 
     */
    public int getProcessExitCode()
    {
        // Make sure it's terminated
        try
        {
            ffmpeg.waitFor();
        }
        catch (InterruptedException ex)
        {
            LOG.warn("Interrupted during waiting on process, forced shutdown?", ex);
        }
        return ffmpeg.exitValue();
    }
}
