package com.foo.outputfomat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class FileRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream hadoopPath;
    private FSDataOutputStream otherpath;

    public FileRecordWriter(TaskAttemptContext job) {
        //创建文件和输出流
        try (FileSystem fs = FileSystem.get(job.getConfiguration());) {
            hadoopPath = fs.create(new Path("F:\\data\\outputformat\\output\\hadoop.txt"));
            otherpath = fs.create(new Path("F:\\data\\outputformat\\output\\other.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String word = key.toString();
        if ("hadoop".contains(word)) {
            hadoopPath.write(word.getBytes());
        } else {
            otherpath.write(word.getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(hadoopPath);
        IOUtils.closeStream(otherpath);
    }
}
