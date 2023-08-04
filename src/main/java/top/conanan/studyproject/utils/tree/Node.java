package top.conanan.studyproject.utils.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node<E extends Node<E>> {

    private String id;

    private String parentId;

    private String name;

    private Integer sort = 0;

    private List<E> children = new ArrayList<>();

}
