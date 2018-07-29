package ssm.controller;


import ssm.model.User;
import ssm.model.Vo;
import ssm.model.constance.StreamAction;
import ssm.model.json.Charge;
import ssm.model.json.Gift;
import ssm.model.json.SrsSteam;
import ssm.redis.RedisHandler;
import ssm.service.CommonService;
import ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/home")
    public String home(Model model, HttpSession session, String keyword, Integer category) {
        User user = (User)session.getAttribute("activeUser");
        if (user!=null){//更新当前用户

            session.setAttribute("activeUser",userService.findUserById(user.getId()));
        }
        Map<String, String> liveMap = redisHandler.getAllLiving();
        List<Vo> liveList = null;
        if (keyword != null) {
            ArrayList<String> keys = new ArrayList<>();
            for (String key : keyword.split(" ")) {
                if (!key.trim().equals("")) {
                    keys.add(key);
                }
            }
            liveList = commonService.getLiveListByKeys(liveMap, keys);
        } else if (category != null) {
            liveList = commonService.getLiveListByCate(liveMap, category);
        } else {

            liveList = commonService.getLiveListFromMap(liveMap);
        }

        if (liveList != null) {
            //sort by viewer
            Collections.sort(liveList, new Comparator<Vo>() {
                @Override
                public int compare(Vo o1, Vo o2) {
                    if (o1.getViewer() >= o2.getViewer()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            model.addAttribute("liveList", liveList);
        }


        return "home";
    }



    @RequestMapping("/srs")
    @ResponseBody
    public Map<String, Integer> publishStream(@RequestBody SrsSteam steam) {
        HashMap<String, Integer> jsonMap = new HashMap<String, Integer>();
        switch (steam.getAction()) {
            case StreamAction.ON_PULISH:
                //register to redis
                redisHandler.registerLiveStream(steam);
                break;
            case StreamAction.ON_UNPULISH:
                //delete from redis
                redisHandler.deleteLiveStream(steam);
                break;
        }

        jsonMap.put("code", 0);
        return jsonMap;
    }


    @RequestMapping("/charge")
    @ResponseBody
    public String charge(@RequestBody Charge charge, HttpSession session) {

        User activeUser = (User) session.getAttribute("activeUser");
        activeUser.setCoin( Double.parseDouble(String.format("%.1f",activeUser.getCoin() + charge.getAmount())));
        commonService.charge(activeUser);

        session.setAttribute("activeUser", activeUser);

        return activeUser.getCoin() + "";
    }

    @RequestMapping("/send_gift")
    @ResponseBody
    public String sendGift(@RequestBody Gift gift, HttpSession session) {

        User activeUser = (User) session.getAttribute("activeUser");
        activeUser = commonService.sendGift(activeUser, gift);

        session.setAttribute("activeUser", activeUser);

        return activeUser.getCoin() + "";
    }


}
