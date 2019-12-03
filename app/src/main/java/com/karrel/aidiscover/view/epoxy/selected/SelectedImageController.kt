package com.karrel.aidiscover.view.epoxy.selected

import com.airbnb.epoxy.TypedEpoxyController

class SelectedImageController : TypedEpoxyController<List<SelectedProfileItem>>() {
    override fun buildModels(data: List<SelectedProfileItem>?) {

        selectedProfileViewHolder {
            id(0)
        }

        plusViewHolder {
            id(11)
        }

        selectedProfileViewHolder {
            id(2)
        }
        plusViewHolder {
            id(12)
        }
        selectedProfileViewHolder {
            id(3)
        }
        plusViewHolder {
            id(13)
        }
        selectedProfileViewHolder {
            id(4)
        }
        plusViewHolder {
            id(14)
        }
        selectedProfileViewHolder {
            id(5)
        }

    }
}