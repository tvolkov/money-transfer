package pack.transfer.rest;

import pack.transfer.api.AccountDao;
import pack.transfer.api.AccountService;
import pack.transfer.api.CurRateService;
import pack.transfer.api.TransferService;
import pack.transfer.api.UserDao;
import pack.transfer.api.UserService;
import pack.transfer.dao.AccountDaoImpl;
import pack.transfer.dao.CurRateDaoImpl;
import pack.transfer.dao.DBManagerImpl;
import pack.transfer.dao.UserDaoImpl;
import pack.transfer.dao.UserDaoOrmLite;
import pack.transfer.service.AccountServiceImpl;
import pack.transfer.service.CurRateServiceImpl;
import pack.transfer.service.TransferServiceImpl;
import pack.transfer.service.UserServiceImpl;

import static pack.transfer.api.DBManager.PROP_FILE_NAME;

public class AppContext {
    private static final UserService userService;
    private static final AccountService accountService;
    private static final CurRateService curRateService;
    private static final TransferServiceImpl transferService;

    static {
        DBManagerImpl dbManager = new DBManagerImpl(PROP_FILE_NAME);
        AccountDao accountDao = new AccountDaoImpl(dbManager);
        curRateService = new CurRateServiceImpl(new CurRateDaoImpl(dbManager));
        transferService = new TransferServiceImpl(accountDao, curRateService);
        UserDao daoImpl = new UserDaoImpl(dbManager);
        UserDao daoOrm = new UserDaoOrmLite(PROP_FILE_NAME);
        userService = new UserServiceImpl(daoOrm);
        accountService = new AccountServiceImpl(accountDao);
    }

    public static CurRateService getCurRateService() {
        return curRateService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static TransferService getTransferService() {
        return transferService;
    }
}
