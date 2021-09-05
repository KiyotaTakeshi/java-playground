package com.kiyotakeshi.EqualsHashCode;

import java.util.Objects;

public class Employee {

    private int employeeNo;

    private String employeeName;

    public Employee(int employeeNo, String employeeName) {
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        // フィールドの値を比較して値が等しいかを判定
        return employeeNo == employee.employeeNo && Objects.equals(employeeName, employee.employeeName);
    }

    // hashCode は自身のオブジェクトの内容を表す数値(ハッシュ値)を返す
    // ・ハッシュ値が異なる場合は異なるオブジェクトとなる
    // ・同じオブジェクトのハッシュ値は必ず同一になる
    // ・異なるオブジェクトでもハッシュ値がおなじになることがある

    // equals は値を1つずつ検証するため計算に時間がかかるため、
    // java.util.HashMap, java.util.HashSet では、
    // ・最初にハッシュ値でオブジェクトを比較し
    // ・ハッシュ値が等しい場合に限り、 equals で厳密な判定を行う

    // そのため、オブジェクトが等価だと判定するには以下の条件が必要
    // ・hashCode の値が同一である
    // ・equals で等価だと判定される

    @Override
    public int hashCode() {
        return Objects.hash(employeeNo, employeeName);
    }
}
