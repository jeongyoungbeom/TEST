package com.young.springboot.domain.posts;

import com.young.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
// 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor
// 기본 생성자 자동 추가
@Entity
/*
    테이블과 링크될 클래스임을 나타냅니다.
    기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭합니다.
    ex) SalesManager.java -> sales_manager table
 */
public class Posts extends BaseTimeEntity {

    @Id
    // 해당 테이블의 PK를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK의 생성 규칙을 나타냅니다.
    private Long id;

    @Column(length = 500, nullable = false)
    /*
        테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다.
        사용하는 이유 : 기본값 외에 변경이 필요한 옵션이 있으면 사용합니다.
                      사이즈를 500으로 늘리고 싶거나, 타입을 변경하고 싶거나 등의 경우에 사용됩니다.
     */
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    /*
        해당 클래스의 빌더 패턴 클래스를 생성
        생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
     */
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
