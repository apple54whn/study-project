package top.conanan.studyproject.utils.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizNode extends Node<BizNode> {

    private Long level;

}
