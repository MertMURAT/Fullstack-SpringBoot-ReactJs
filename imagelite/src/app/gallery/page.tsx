"use client"
import { Template, ImageCard } from "@/components";
import { Image } from "@/resources/image/image.resources";
import { useImageService } from "@/resources/image/image.service";
import { useState } from "react";

export default function GalleryPages() {
    const [images, setImages] = useState<Image[]>([]);
    const userService = useImageService();

    async function searchImages() {
        const result = await userService.search();
        setImages(result);
        console.table(result);
        console.table("IMAGES : " + images);
    }

    function renderImageCard(image: Image) {
        return (
            <div key={image.id}>
                <ImageCard
                    src={image.url}
                    name={image.name}
                    size={image.size}
                    dataUpload={image.uploadDate} />
            </div>
        );
    }

    function renderImageCards() {
        // return images.map(image => renderImageCard(image));
        return images.map(renderImageCard);
    }

    return (
        <Template>
            <section className="flex flex-col items-center justify-center my-5">
                <div className="flex space-x-4">
                    <input type="text" className="border px-3 py-2 rounded-lg text-gray-900" />
                    <select className="border px-4 py-2 rounded-lg text-gray-900">
                        <option>All formats</option>
                    </select>
                    <button
                        onClick={searchImages}
                        className="bg-blue-500 text-white px-4 py-2 rounded-lg">Search</button>
                    <button className="bg-yellow-500 text-white px-4 py-2 rounded-lg">Add New</button>
                </div>
            </section>
            <h4 className="font-bold text-3xl py-2">Gallery</h4>
            <section className="grid grid-cols-4 gap-8">
                {
                    renderImageCards()
                }


            </section>
        </Template>
    );
}