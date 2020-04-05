package com.qiao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Resource implements Serializable {
    private String resourceId;
    private String resourceName;

}
