package test;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String name;
  private List<String> images;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", images=" + images + "]";
  }
}
