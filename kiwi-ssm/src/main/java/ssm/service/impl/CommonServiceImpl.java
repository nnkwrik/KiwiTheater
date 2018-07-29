package ssm.service.impl;

import ssm.dao.CommonMapper;
import ssm.dao.UserMapper;
import ssm.model.User;
import ssm.model.Vo;
import ssm.model.json.Gift;
import ssm.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UserMapper userMapper;


    public List<Vo> getLiveListFromMap(Map<String, String> liveMap) {
        if (liveMap.size() < 1) return null;

        //mybatis 不接受set
        ArrayList<Integer> roomids = new ArrayList<>();
        for (String strId : liveMap.keySet()) {
            roomids.add(Integer.parseInt(strId));
        }

        List<Vo> liveVos = commonMapper.findSimpleVoListByRooomIds(roomids);

        return filterVo(liveMap, liveVos);
    }

    public List<Vo> getLiveListByKeys(Map<String, String> liveMap, List<String> keywords) {
        List<Vo> liveVos = commonMapper.findSimpleVoListByKeys(keywords);
        return filterVo(liveMap, liveVos);
    }

    public List<Vo> getLiveListByCate(Map<String, String> liveMap, Integer category) {

        List<Vo> liveVos = commonMapper.findSimpleVoListByCate(category);
        return filterVo(liveMap, liveVos);
    }

    private static List<Vo> filterVo(Map<String, String> liveMap, List<Vo> liveVos) {
        ArrayList<Vo> finalVos = new ArrayList<>();

        for (Vo vo : liveVos) {
            String viewer = liveMap.get(vo.getRoom().getId() + "");
            if (viewer != null) {
                vo.setViewer(Integer.parseInt(viewer));
                finalVos.add(vo);
            }

        }
        return finalVos;
    }

    // 上传图片
    public String uploadPic(String dir, MultipartFile pic) throws IOException {

        // 上传图片
        // 原始名称
        String originalFilename = pic.getOriginalFilename();

        // 存储图片的物理路径
        String pic_path = "pic/" + dir + "/";

        // 新的图片名称 : 随机数 + 扩展名
        String newfileNStringame = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 新图片
        File newfile = new File(pic_path + newfileNStringame);

        // 将内存中的数据写入磁盘

        pic.transferTo(newfile);


        return newfileNStringame;

    }

    public void charge(User user) {
        commonMapper.updateCoin(user);
    }

    public User sendGift(User self, Gift gift) {
        //TODO 事务
        User anchor = userMapper.findUserById(gift.getAnchorid());
        anchor.setCoin( Double.parseDouble(String.format("%.1f",anchor.getCoin() + gift.getGiftValue())));
        self.setCoin( Double.parseDouble(String.format("%.1f",self.getCoin() - gift.getGiftValue())));
        self.setUsedcoin( Double.parseDouble(String.format("%.1f",self.getUsedcoin() + gift.getGiftValue())));
        commonMapper.updateCoin(anchor);
        commonMapper.updateCoin(self);

        return self;

    }
}
