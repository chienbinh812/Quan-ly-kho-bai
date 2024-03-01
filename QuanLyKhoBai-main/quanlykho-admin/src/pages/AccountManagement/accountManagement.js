import React, { useState, useEffect } from 'react';
import "./accountManagement.css";
import { Button, Spin, Row, Card, Popconfirm, Table, Input, Col, notification, BackTop, Tag, Breadcrumb, Space, Popover } from 'antd';
import { HomeOutlined, PlusOutlined, UserOutlined, RedoOutlined, StopOutlined, CheckCircleOutlined, CopyOutlined, EditOutlined, SecurityScanOutlined } from '@ant-design/icons';
import userApi from "../../apis/userApi";
import productApi from "../../apis/productApi";

import { useHistory } from 'react-router-dom';
import { PageHeader } from '@ant-design/pro-layout';

const QuestionManagement = () => {

    const [user, setUser] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(1);
    const [selectedInput, setSelectedInput] = useState();
    const [originalCategoryList, setOriginalCategoryList] = useState([]);
    const [displayedCategoryList, setDisplayedCategoryList] = useState([]);
    const [searchText, setSearchText] = useState('');
    const history = useHistory();

    const titleCase = (str) => {
        var splitStr = str?.toLowerCase().split(' ');
        for (var i = 0; i < splitStr.length; i++) {
            // You do not need to check if i is larger than splitStr length, as your for does that for you
            // Assign it back to the array
            splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);
        }
        // Directly return the joined string
        return splitStr.join(' ');
    }

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'index',
            render: (value, item, index) => (
                value
            ),
        },
        {
            title: 'Tên',
            dataIndex: 'username',
            key: 'username',
            sorter: (a, b) => a.id - b.id,
            render: (text, record) => (
                <Space size="middle">
                    {
                        text == null || text == undefined ? "" :
                            <p style={{ margin: 0 }}>{titleCase(text)}</p>
                    }

                </Space>
            ),
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
            sorter: (a, b) => a.id - b.id,
        },
        {
            title: 'Admin',
            dataIndex: 'admin',
            key: 'admin',
            width: '12%',
            render: (text, record) => (
                <Space size="middle">
                    {

                        text === true ?
                            <Tag color="blue" key={text} style={{ width: 140, textAlign: "center" }} icon={<CopyOutlined />}>
                                Là Admin
                            </Tag> : <Tag color="green" key={text} style={{ width: 140, textAlign: "center" }} icon={<CheckCircleOutlined />}>
                                Không phải Admin
                            </Tag>
                    }

                </Space>
            ),
        },
        {
            title: 'Ngày tạo',
            dataIndex: 'createAt',
            key: 'createAt',
            render: (text, record) => (
                <Space size="middle">
                    {

                        <Tag color="cyan" key={text} style={{ width: 140, textAlign: "center" }}>
                            {text}
                        </Tag>
                    }

                </Space>
            ),
        },
        {
            title: 'Action',
            key: 'action',
            render: (text, record) => (
                <div>
                    <Row>
                        <Popconfirm
                            title="Bạn có muốn chắc chắn xóa câu hỏi này?"
                            onConfirm={() => handleUnBanAccount(record)}
                            okText="Yes"
                            cancelText="No"
                        >
                            <Button
                                size="small"
                                icon={<CheckCircleOutlined />}
                                style={{ width: 160, borderRadius: 15, height: 30 }}
                            >{"Xóa tài khoản"}
                            </Button>
                        </Popconfirm>
                    </Row>

                </div >
            ),
        },
    ];

    const handleUnBanAccount = async (data) => {
        console.log(data)
        try {
            await userApi.unBanAccount(data.id).then(response => {
                if (response === undefined) {
                    notification["error"]({
                        message: `Thông báo`,
                        description:
                            'Xóa tài khoản thất bại',

                    });
                }
                else {
                    notification["success"]({
                        message: `Thông báo`,
                        description:
                            'Xóa tài khoản thành công',

                    });
                    handleData();
                }
            }
            );

        } catch (error) {
            console.log('Failed to fetch event list:' + error);
        }
    }

    const handleBanAccount = async (data) => {
        console.log(data);
        try {
            await userApi.banAccount(data.id).then(response => {
                if (response === undefined) {
                    notification["error"]({
                        message: `Thông báo`,
                        description:
                            'Chặn phê duyệt thất bại',

                    });
                }
                else {
                    notification["success"]({
                        message: `Thông báo`,
                        description:
                            'Chặn phê duyệt thành công',

                    });
                    handleData();
                }
            }
            );

        } catch (error) {
            console.log('Failed to fetch event list:' + error);
        }
    }

    const handleFilterEmail = async (name) => {
        try {
            if (name.target.value) {
                console.log(name.target.value);
                const filteredCategory = originalCategoryList.filter((item) =>
                    item.username.toLowerCase().includes(name.target.value.toLowerCase())
                );
                setDisplayedCategoryList(filteredCategory);
            } else {
                handleData();
            }
        } catch (error) {
            console.log('search to fetch category list:' + error);
        }
    }

    const handleData = () => {
        (async () => {
            try {
                const response = await userApi.listUser();
                setOriginalCategoryList(response);
                setDisplayedCategoryList(response);
                setLoading(false);
            } catch (error) {
                console.log('Failed to fetch user list:' + error);
            }
        })();
    }

    useEffect(() => {
        handleData();
        window.scrollTo(0, 0);

    }, [])

    const [openModalCreate, setOpenModalCreate] = useState(false);
    const [openModalUpdate, setOpenModalUpdate] = useState(false);

    const showModal = () => {
        setOpenModalCreate(true);
    };
    return (
        <div>
            <Spin spinning={loading}>
                <div style={{ marginTop: 20, marginLeft: 24 }}>
                    <Breadcrumb>
                        <Breadcrumb.Item href="">
                            <HomeOutlined />
                        </Breadcrumb.Item>
                        <Breadcrumb.Item href="">
                            <UserOutlined />
                            <span>Quản lý câu hỏi</span>
                        </Breadcrumb.Item>
                    </Breadcrumb>
                </div>
                <div id="account">
                    <div id="account_container">
                        <PageHeader
                            subTitle=""
                            style={{ fontSize: 14, paddingTop: 20, paddingBottom: 20 }}
                        >
                            <Row>
                                <Col span="12">
                                    <Input
                                        placeholder="Tìm kiếm theo tên"
                                        allowClear
                                        style={{ width: 300 }}
                                        onChange={handleFilterEmail}
                                        value={selectedInput}
                                    />
                                </Col>
                                <Col span="12">
                                    <Row justify="end">
                                        {/* <Button onClick={showModal} icon={<PlusOutlined />} style={{ marginLeft: 10 }} >Tạo tài khoản</Button> */}
                                    </Row>
                                </Col>
                            </Row>

                        </PageHeader>
                    </div>
                </div>
                <div style={{ marginTop: 20, marginRight: 5 }}>
                    <div id="account">
                        <div id="account_container">
                            <Card title="Quản lý câu hỏi" bordered={false} >
                                <Table columns={columns} dataSource={displayedCategoryList} pagination={{ position: ['bottomCenter'] }}
                                />
                            </Card>
                        </div>
                    </div>
                </div>
                <BackTop style={{ textAlign: 'right' }} />
            </Spin>
        </div>
    )
}

export default QuestionManagement;