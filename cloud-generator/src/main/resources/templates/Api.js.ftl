import request from '@/utils/request'


export function findPage(data) {
    return request({
        url: '/${moduleName}/${pathName}/page',
        method: 'get',
        params: data
    })
}


export function getById(id) {
    return request({
        url: '/${moduleName}/${pathName}/' + id,
        method: 'get'
    })
}

export function updateById(data) {
    return request({
        url: '/${moduleName}/${pathName}',
        method: 'put',
        data
    })
}


export function saveData(data) {
    return request({
        url: '/${moduleName}/${pathName}',
        method: 'post',
        data
    })
}

export function delById(id) {
    return request({
        url: '/${moduleName}/${pathName}/' + id,
        method: 'delete'
    })
}
