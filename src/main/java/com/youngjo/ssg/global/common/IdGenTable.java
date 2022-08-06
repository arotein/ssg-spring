package com.youngjo.ssg.global.common;

import javax.persistence.TableGenerator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@TableGenerator(name = SeqTable.name,
        pkColumnValue = SeqTable.pkColumnValue,
        initialValue = 1,
        allocationSize = 100)
@Target(ElementType.TYPE)
public @interface IdGenTable {
}
