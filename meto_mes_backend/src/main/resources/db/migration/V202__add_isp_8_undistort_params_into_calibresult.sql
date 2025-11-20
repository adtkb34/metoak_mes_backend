alter table calibresult
    add ISP_left_k4 float(10, 6) null after ISP_left_k3;

alter table calibresult
    add ISP_left_k5 float(10, 6) null after ISP_left_k4;

alter table calibresult
    add ISP_left_k6 float(10, 6) null after ISP_left_k5;

alter table calibresult
    add ISP_right_k4 float(10, 6) null after ISP_right_k3;

alter table calibresult
    add ISP_right_k5 float(10, 6) null after ISP_right_k4;

alter table calibresult
    add ISP_right_k6 float(10, 6) null after ISP_right_k5;
