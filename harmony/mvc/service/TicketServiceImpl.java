package mvc.service;

import mvc.dao.TicketDAO;
import mvc.dao.TicketDAOImpl;
import mvc.dto.TicketDTO;
import mvc.exception.DMLException;
import mvc.exception.SearchWrongException;
import mvc.view.FailView;
import mvc.view.SuccessView;

import java.util.List;

public class TicketServiceImpl implements TicketService{
    private static final TicketService instance = new TicketServiceImpl();

    private final TicketDAO ticketDAO = TicketDAOImpl.getInstance();

    private TicketServiceImpl() {}
    public static TicketService getInstance() {
        return instance;
    }

    /**
     * 예매 등록
     * */
    @Override
    public void ticketInsert(TicketDTO ticket) throws DMLException {
        int result = ticketDAO.ticketInsert(ticket);

        if (result == 0) {
            throw new DMLException("실패");
        }
    }

    /**
     * 예매 취소
     */
    @Override
    public void ticketDelete(int ticketID) throws DMLException {
        int result = ticketDAO.ticketDelete(ticketID);

        if (result == 0) {
            throw new DMLException("예매 취소 실패");
        }
    }

    /**
     * 개별 유저 예매 내역 조회
     **/
    @Override
    public TicketDTO ticketSelectByTicketId(int ticketID){
        TicketDTO ticketDTO = ticketDAO.ticketSelectByTicketId(ticketID);

        if (ticketDTO == null) {
            throw new SearchWrongException("예매 번호와 일치하는 예매 내역이 없습니다.");
        }

        return ticketDTO;
    }

    @Override
    public List<TicketDTO> ticketSelectByUserId(String userId) throws SearchWrongException {
        List<TicketDTO> ticketDTOList = ticketDAO.ticketSelectByUserId(userId);

        if (ticketDTOList.size() == 0) {
            throw new SearchWrongException("사용자 정보와 일치하는 예매 내역이 없습니다.");
        }

        return ticketDTOList;
    }
}
