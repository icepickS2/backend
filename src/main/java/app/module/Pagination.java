package app.module;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
  private int size = 10;
  private int numberOfElements = 10;

  private int totalPage;
  private int totalElement;

  private int start, offset, end;
  private boolean first, prev, next, last;

  public Pagination paging(int offset, int totalElement) {
    this.offset = offset;
    this.totalElement = totalElement;

    this.totalPage = (int) Math.ceil((double) (totalElement) / size);

    // 일반적인 경우
    int prevCount = (size - 1) / 2;
    int postCount = size / 2;

    this.start = this.offset - prevCount;
    this.start = this.start < 1 ? 1 : this.start;
    this.end = this.offset + postCount;
    this.end = this.end > totalPage ? totalPage : this.end;

    // prevCount가 짧은경우
    if (this.offset - this.start < prevCount) {
      prevCount = this.offset - this.start;
      postCount = this.size - prevCount - 1;
    }

    // postCount가 짧은경우
    else if (this.end - this.offset < postCount) {
      postCount = this.end - this.offset;
      prevCount = this.size - postCount - 1;
    }

    this.start = this.offset - prevCount;
    this.start = this.start < 1 ? 1 : this.start;
    this.end = this.offset + postCount;
    this.end = this.end > totalPage ? totalPage : this.end;

    prev = offset - prevCount > 1 ? true : false;
    first = prev;
    next = offset + postCount < totalPage ? true : false;
    last = next;

    return this;
  }

}