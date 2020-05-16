import { findPage, getById, updateById, saveData, delById } from '@/api/${moduleName}/${classname}'

const actions = {

    // 获取列表
    findPage({ commit }, param) {
        return new Promise((resolve, reject) => {
            findPage(param).then(response => {
                const { data } = response
                resolve(data)
            }).catch(error => {
            reject(error)
            })
        })
    },

    getById({ commit }, id) {
        return new Promise((resolve, reject) => {
            getById(id).then(response => {
                const { data } = response
                resolve(data)
            }).catch(error => {
            reject(error)
            })
        })
    },
    updateById({ commit }, param) {
        return new Promise((resolve, reject) => {
            updateById(param).then(response => {
                const { data } = response
                resolve(data)
            }).catch(error => {
            reject(error)
            })
        })
    },

    saveData({ commit }, param) {
        return new Promise((resolve, reject) => {
            saveData(param).then(response => {
                const { data } = response
                resolve(data)
            }).catch(error => {
            reject(error)
            })
        })
    },

    delById({ commit }, id) {
        return new Promise((resolve, reject) => {
            delById(id).then(res => {
                resolve()
            }).catch(error => {
            reject(error)
            })
        })
    }

}

export default {
    namespaced: true,
    actions
}
