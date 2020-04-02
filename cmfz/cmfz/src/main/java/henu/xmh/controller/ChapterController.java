package henu.xmh.controller;

import henu.xmh.pojo.Chapter;
import henu.xmh.service.ChapterService;
import henu.xmh.util.FileUploadUtil;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("findOneChapters")
    public Map<String,Object> findOneChapters(Boolean _search,String albumId, String searchString, String searchOper,
                                              String searchField, Integer rows, Integer page){
        Map<String, Object> map = null;
        if(!_search){
           map = chapterService.findOneChaptersByAlbumIdForPage(albumId,page,rows);
        }else {//条件搜索

        }
        return map;
    }
    @RequestMapping("upload")
    public void upload(String chapterId, MultipartFile voiceurl, HttpServletRequest request) throws ReadOnlyFileException, TagException, InvalidAudioFrameException, IOException {
        String httpUrl = FileUploadUtil.upload(voiceurl, request, "/upload/audio");
        //获取音频文件的大小和时长
        String fileNewName = httpUrl.split("/")[6];
        String realPath = request.getRealPath("/upload/audio");
        MP3File mp3File = new MP3File(realPath + "/" + fileNewName);
        MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
        String timeLong = audioHeader.getTrackLengthAsString();//时长
        Double size = Double.valueOf(voiceurl.getSize());
        DecimalFormat df=new DecimalFormat("0.00");
        String format = df.format(size / (1024 * 1024));
        Chapter chapter = new Chapter().setChapterId(chapterId).setVoiceurl(httpUrl)
                .setTimeLong(timeLong).setSize(format+"M");
        chapterService.alter(chapter);
    }
    @RequestMapping("alter")
    public Map<String,Object> alter(String oper,Chapter chapter,String[] id,String albumId,HttpServletRequest request){
        Map<String, Object> map = chapterService.editForJq(oper, chapter, id,albumId,request);
        return map;
    }

    @RequestMapping("download")
    public void download(String voiceurl, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = voiceurl.split("/")[6];
        String realPath = request.getRealPath("/upload/audio");
        File file = new File(realPath + "/" + fileName);
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        FileInputStream is = new FileInputStream(file);
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

}
