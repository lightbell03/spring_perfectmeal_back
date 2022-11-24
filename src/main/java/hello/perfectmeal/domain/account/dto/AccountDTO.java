package hello.perfectmeal.domain.account.dto;

import hello.perfectmeal.domain.account.Account;
import lombok.Getter;

@Getter
public class AccountDTO {
    private Long id;
    private String email;

    public static AccountDTO AccountToAccountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.email = account.getName();
        accountDTO.id = account.getId();

        return accountDTO;
    }
}
