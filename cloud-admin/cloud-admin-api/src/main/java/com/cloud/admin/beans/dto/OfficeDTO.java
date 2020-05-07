package com.cloud.admin.beans.dto;


import com.cloud.admin.beans.po.SysOffice;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Aijm
 * @since 2019-05-04
 */
@Data
@Accessors(chain = true)
public class OfficeDTO extends SysOffice {


    /**
     * 主负责人
     */
    private UserDTO priPerson;

    /**
     * 主负责人
     */
    private UserDTO depPerson;
}
