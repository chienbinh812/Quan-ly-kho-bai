import React, { useState, useEffect } from 'react';
import "./subCategoryList.css";
import {
    Col, Row, Spin, Button, Input, Space,
    Form, Modal, Popconfirm, notification, BackTop, Breadcrumb, Table, DatePicker
} from 'antd';
import {
    DeleteOutlined, PlusOutlined, HomeOutlined, ShoppingOutlined, EditOutlined
} from '@ant-design/icons';
import warehouseApi from "../../apis/warehouseApi";
import { useHistory } from 'react-router-dom';
import axiosClient from '../../apis/axiosClient';
import { PageHeader } from '@ant-design/pro-layout';
import moment from 'moment';

const SubCategoryList = () => {
    const [originalCategoryList, setOriginalCategoryList] = useState([]);
    const [displayedCategoryList, setDisplayedCategoryList] = useState([]);
    const [searchText, setSearchText] = useState('');

    const [category, setCategory] = useState([]);
    const [openModalCreate, setOpenModalCreate] = useState(false);
    const [openModalUpdate, setOpenModalUpdate] = useState(false);
    const [loading, setLoading] = useState(true);
    const [form] = Form.useForm();
    const [form2] = Form.useForm();
    const [total, setTotalList] = useState();
    const [currentPage, setCurrentPage] = useState(1);
    const [id, setId] = useState();
    const [image, setImage] = useState();

    const history = useHistory();

    const showModal = () => {
        setOpenModalCreate(true);
    };

    const handleOkUser = async (values) => {
        setLoading(true);
        try {
            const categoryList = {
                "name": values.name,
                "price": values.price,
                "address": values.address,
            }
            return axiosClient.post("/warehouse", categoryList).then(response => {
                if (response === undefined) {
                    notification["error"]({
                        message: `Thông báo`,
                        description:
                            'Tạo kho hàng thất bại',
                    });
                }
                else {
                    notification["success"]({
                        message: `Thông báo`,
                        description:
                            'Tạo kho hàng thành công',
                    });
                    setOpenModalCreate(false);
                    handleData();
                }
            })

        } catch (error) {
            throw error;
        }
    }

    const handleUpdateCategory = async (values) => {
        console.log(image);
        setLoading(true);
        try {
            const categoryList = {
                "id": id,
                "name": values.name,
                "address": values.address,
            }
            return axiosClient.put("/warehouse/" + id , categoryList).then(response => {
                if (response === undefined) {
                    notification["error"]({
                        message: `Thông báo`,
                        description:
                            'Chỉnh sửa kho hàng thất bại',
                    });
                }
                else {
                    notification["success"]({
                        message: `Thông báo`,
                        description:
                            'Chỉnh sửa kho hàng thành công',
                    });
                    handleData();
                    setOpenModalUpdate(false);
                }
            })

        } catch (error) {
            throw error;
        }
    }

    const handleCancel = (type) => {
        if (type === "create") {
            setOpenModalCreate(false);
        } else {
            setOpenModalUpdate(false)
        }
        console.log('Clicked cancel button');
    };

    const handleDeleteCategory = async (id) => {
        setLoading(true);
        try {
            await warehouseApi.deleteOne(id).then(response => {
                if (response === undefined) {
                    notification["error"]({
                        message: `Thông báo`,
                        description:
                            'Xóa kho hàng thất bại',

                    });
                    setLoading(false);
                }
                else {
                    notification["success"]({
                        message: `Thông báo`,
                        description:
                            'Xóa kho hàng thành công',

                    });
                    setCurrentPage(1);
                    handleData();
                    setLoading(false);
                }
            }
            );

        } catch (error) {
            console.log('Failed to fetch event list:' + error);
        }
    }

    const handleEditCategory = (id) => {
        setOpenModalUpdate(true);
        (async () => {
            try {
                const response = await warehouseApi.get(id);
                setId(id);
                form2.setFieldsValue({
                    name: response.name,
                    address: response.address,

                });
                console.log(form2);
                setLoading(false);
            } catch (error) {
                throw error;
            }
        })();
    }

    const handleFilter = async (name) => {
        try {
            if (name.target.value) {
                console.log(name.target.value);
                setSearchText(name.target.value); // Lưu giá trị ô tìm kiếm vào trạng thái
                // Lọc danh sách danh mục dựa trên searchText
                const filteredCategory = originalCategoryList.filter((item) =>
                    item.name.toLowerCase().includes(searchText.toLowerCase())
                );
                setDisplayedCategoryList(filteredCategory);
            } else {
                handleData();
            }
        } catch (error) {
            console.log('search to fetch category list:' + error);
        }
    }

    const columns = [
        {
            title: 'ID',
            key: 'index',
            render: (text, record, index) => index + 1,
            sorter: (a, b) => a.id - b.id,
        },
        {
            title: 'Tên',
            dataIndex: 'name',
            key: 'name',
            render: (text) => <a>{text}</a>,
            sorter: (a, b) => a.id - b.id,
        },
        {
            title: 'Địa chỉ',
            dataIndex: 'address',
            key: 'address',
            render: (text) => <a>{text}</a>,
            sorter: (a, b) => a.id - b.id,
        },
        {
            title: 'Action',
            key: 'action',
            render: (text, record) => (
                <div>
                    <Row>
                        <Button
                            size="small"
                            icon={<EditOutlined />}
                            style={{ width: 150, borderRadius: 15, height: 30 }}
                            onClick={() => handleEditCategory(record.id)}
                        >{"Chỉnh sửa"}
                        </Button>
                        <div
                            style={{ marginLeft: 10 }}>
                            <Popconfirm
                                title="Bạn có chắc chắn xóa danh mục này?"
                                onConfirm={() => handleDeleteCategory(record.id)}
                                okText="Yes"
                                cancelText="No"
                            >
                                <Button
                                    size="small"
                                    icon={<DeleteOutlined />}
                                    style={{ width: 150, borderRadius: 15, height: 30 }}
                                >{"Xóa"}
                                </Button>
                            </Popconfirm>
                        </div>
                    </Row>
                </div >
            ),
        },
    ];

    const handleData = () => {
        (async () => {
            try {
                await warehouseApi.getAll().then((res) => {
                    setOriginalCategoryList(res.content);
                    setDisplayedCategoryList(res.content);
                    setLoading(false);
                });
            } catch (error) {
                console.log('Failed to fetch category list:' + error);
            }
        })();
    }


    useEffect(() => {
        handleData();
    }, [])
    return (
        <div>
            <Spin spinning={loading}>
                <div className='container'>
                    <div style={{ marginTop: 20 }}>
                        <Breadcrumb>
                            <Breadcrumb.Item href="">
                                <HomeOutlined />
                            </Breadcrumb.Item>
                            <Breadcrumb.Item href="">
                                <ShoppingOutlined />
                                <span>Quản lý kho hàng</span>
                            </Breadcrumb.Item>
                        </Breadcrumb>
                    </div>

                    <div style={{ marginTop: 20, marginBottom: 20 }}>
                        <div id="my__event_container__list">
                            <PageHeader
                                subTitle=""
                                style={{ fontSize: 14, paddingTop: 15, paddingBottom: 15 }}
                            >
                                <Row>
                                    <Col span="18">
                                        <Input
                                            placeholder="Tìm kiếm theo tên"
                                            allowClear
                                            onChange={handleFilter}
                                            style={{ width: 300 }}
                                        />
                                    </Col>
                                    <Col span="6">
                                        <Row justify="end">
                                            <Space>
                                                <Button onClick={showModal} icon={<PlusOutlined />} style={{ marginLeft: 10 }} >Tạo kho hàng</Button>
                                            </Space>
                                        </Row>
                                    </Col>
                                </Row>

                            </PageHeader>
                        </div>
                    </div>

                    <div style={{ marginTop: 30 }}>
                        <Table columns={columns} pagination={{ position: ['bottomCenter'] }} dataSource={displayedCategoryList} />
                    </div>
                </div>

                <Modal
                    title="Tạo kho hàng mới"
                    visible={openModalCreate}
                    style={{ top: 100 }}
                    onOk={() => {
                        form
                            .validateFields()
                            .then((values) => {
                                form.resetFields();
                                handleOkUser(values);
                            })
                            .catch((info) => {
                                console.log('Validate Failed:', info);
                            });
                    }}
                    onCancel={() => handleCancel("create")}
                    okText="Hoàn thành"
                    cancelText="Hủy"
                    width={600}
                >
                    <Form
                        form={form}
                        name="eventCreate"
                        layout="vertical"
                        initialValues={{
                            residence: ['zhejiang', 'hangzhou', 'xihu'],
                            prefix: '86',
                        }}
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label="Tên"
                            rules={[
                                {
                                    required: true,
                                    message: 'Vui lòng nhập tên!',
                                },
                            ]}
                            style={{ marginBottom: 10 }}
                        >
                            <Input placeholder="Tên" />
                        </Form.Item>

                        <Form.Item
                            name="address"
                            label="Địa chỉ"
                            rules={[
                                {
                                    required: true,
                                    message: 'Vui lòng nhập địa chỉ!',
                                },
                            ]}
                            style={{ marginBottom: 10 }}
                        >
                            <Input placeholder="Địa chỉ" />
                        </Form.Item>

                    </Form>
                </Modal>

                <Modal
                    title="Chỉnh sửa kho hàng"
                    visible={openModalUpdate}
                    style={{ top: 100 }}
                    onOk={() => {
                        form2
                            .validateFields()
                            .then((values) => {
                                form2.resetFields();
                                handleUpdateCategory(values);
                            })
                            .catch((info) => {
                                console.log('Validate Failed:', info);
                            });
                    }}
                    onCancel={handleCancel}
                    okText="Hoàn thành"
                    cancelText="Hủy"
                    width={600}
                >
                    <Form
                        form={form2}
                        name="eventCreate"
                        layout="vertical"
                        initialValues={{
                            residence: ['zhejiang', 'hangzhou', 'xihu'],
                            prefix: '86',
                        }}
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label="Tên"
                            rules={[
                                {
                                    required: true,
                                    message: 'Please input your sender name!',
                                },
                            ]}
                            style={{ marginBottom: 10 }}
                        >
                            <Input placeholder="Tên" />
                        </Form.Item>

                        <Form.Item
                            name="address"
                            label="Địa chỉ"
                            rules={[
                                {
                                    required: true,
                                    message: 'Vui lòng nhập địa chỉ!',
                                },
                            ]}
                            style={{ marginBottom: 10 }}
                        >
                            <Input placeholder="Địa chỉ" />
                        </Form.Item>

                    </Form>
                </Modal>

                <BackTop style={{ textAlign: 'right' }} />
            </Spin>
        </div >
    )
}

export default SubCategoryList;