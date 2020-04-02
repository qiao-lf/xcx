package henu.xmh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CfAdmin implements Serializable {
    @Id
    private String username;

    private String password;

    private List<Role> roles;

    //jqgrid接收主键
    private String id;
}