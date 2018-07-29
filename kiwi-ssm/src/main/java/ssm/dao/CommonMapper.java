package ssm.dao;

import ssm.model.User;
import ssm.model.Vo;

import java.util.List;

public interface CommonMapper {
    List<Vo> findSimpleVoListByRooomIds(List<Integer> roomid);

    List<Vo> findSimpleVoListByKeys(List<String> keywords);
    List<Vo> findSimpleVoListByCate(Integer category);
    void updateCoin(User user);




}
