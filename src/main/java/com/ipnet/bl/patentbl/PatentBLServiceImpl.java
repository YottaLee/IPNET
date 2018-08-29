package com.ipnet.bl.patentbl;

import com.ipnet.vo.PatentVO;
import com.ipnet.blservice.PatentBLService;
import com.ipnet.dao.PatentDao;
import com.ipnet.entity.Patent;
import com.ipnet.enums.Patent_state;
import com.ipnet.utility.IDNotExistsException;
import com.ipnet.utility.TransHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lzb
 * @date 2018/7/21 10:35
 */
@Service
public class PatentBLServiceImpl implements PatentBLService {

    @Autowired
    private PatentDao patentDao;

    @Autowired
    private TransHelper transHelper;

    @Override
    public PatentVO createPatent(PatentVO newPatent) {
        Patent patent = (Patent) this.transHelper.transTO(newPatent , Patent.class);
        Patent realPatent = this.patentDao.saveAndFlush(patent);
        PatentVO resultVO = (PatentVO) this.transHelper.transTO(realPatent , PatentVO.class);
        return resultVO;
    }

    @Override
    public PatentVO searchPatentByID(String patentID) {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentID);
        if (optionalPatent.isPresent() ==false){
            return null;
        }
        PatentVO resultVO = (PatentVO) this.transHelper.transTO(optionalPatent.get() , PatentVO.class);
        return resultVO;
    }

    @Override
    public List<PatentVO> searchPatentByName(String name) {
        List<Patent> patentList = this.patentDao.searchPatentByPatentName(name);
        if (patentList == null || patentList.size()==0){
            return null;
        }
        List<PatentVO> voList = patentList.stream()
                .filter(patent -> patent!=null)
                .map(patent -> (PatentVO)this.transHelper.transTO(patent , PatentVO.class))
                .collect(Collectors.toList());
        return voList;
    }

    @Override
    public Boolean deletePatent(String patentID) {
        this.patentDao.deleteById(patentID);
        return true;
    }

    @Override
    public Boolean updatePatentState(Patent_state newState, String patentID) throws IDNotExistsException {
        Optional<Patent> optionalPatent = this.patentDao.findById(patentID);
        if (optionalPatent.isPresent() == false){
            throw new IDNotExistsException("patent id not exists");
        }
        Patent patent = optionalPatent.get();
        patent.setState(newState);
        Patent resultPatent = this.patentDao.saveAndFlush(patent);
        return true;
    }
}
