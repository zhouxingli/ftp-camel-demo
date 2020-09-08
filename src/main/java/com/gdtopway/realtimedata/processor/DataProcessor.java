package com.gdtopway.realtimedata.processor;

import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.service.StateService;
import com.gdtopway.realtimedata.util.FileRename;
import com.gdtopway.realtimedata.util.OperateFile;
//import com.gdtopway.realtimedata.util.YearMaxLoad;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by rainbow on 2018/11/6.
 * 调度数据处理器
 */
@Component
public class DataProcessor implements Processor {

    @Value("${sftp.local-dir}")
    private String localDir;
    @Autowired
    private OperateFile operateFile;
    @Autowired
    private FileRename renameFile;
   /* @Autowired
    private YearMaxLoad yearMaxLoad;
*/

    private static final Logger logger = LoggerFactory.getLogger(DataProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        String fileName = null;
        fileName = inFileMessage.getGenericFile().getFileName();
        String filePath = localDir + '/' + fileName;
        File file = new File(filePath);
   /*     if(fileName.equals("a.txt")){
            if (file.exists()) {
                yearMaxLoad.yearMaxFileLoad(file);
            }

        }else*/
            if(fileName.equals("ereal.txt")){

            State state = new State();
            if (file.exists()) {
                //1.调用重命名文件
                File renamefile = renameFile.reNameFile(localDir, fileName, state);
                //2.调用处理文件
                operateFile.doFile(renamefile, state);
            }
        }

    }
}
