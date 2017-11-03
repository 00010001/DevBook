package com.devbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * Created by RENT on 2017-11-03.
 */

@Data
@NoArgsConstructor
public class Role {

    @Id
    private String _id;

    private String name;
    private String description;

}
